package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.login.LoginComponent
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity
import com.example.android.dagger.registration.RegistrationComponent
import com.example.android.dagger.registration.enterdetails.EnterDetailsFragment
import com.example.android.dagger.registration.termsandconditions.TermsAndConditionsFragment
import com.example.android.dagger.settings.SettingsActivity
import com.example.android.dagger.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Dagger Graph
// @Singleton annotation on a @Component allows us "to scope a type to the Component's lifecycle" so
// all classes annotated with @Singleton will be scoped to the Component's lifetime.
// Dagger component definition that adds info from the StorageModule to the graph
@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {
    // Factory to create instances of the AppComponent
    // @Component.Factory Generates a static "factory()" method in DaggerAppComponent.java that
    // returns a new implementation of this type of interface.
    @Component.Factory
    interface Factoryyyy {
        // With @BindsInstance, the context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Expose RegistrationComponent factory from the graph by declaring a function with that class
    // as return type.
    //
    // There are two different ways to interact with the Dagger graph:
    //
    // Declaring a function that returns Unit and takes a class as a parameter allows field injection
    // in that class (e.g. fun inject(activity: MainActivity)).
    //
    // Declaring a function that returns a type allows retrieving types from the graph (e.g.
    // fun registrationComponent(): RegistrationComponent.Factory).

    // Types that can be retrieved from the graph
    fun loginComponent(): LoginComponent.Factory
    fun registrationComponent(): RegistrationComponent.Factory

    // Expose UserManager so that MainActivity and SettingsActivity can access a particular
    // instance of UserComponent
    fun userManager(): UserManager
}