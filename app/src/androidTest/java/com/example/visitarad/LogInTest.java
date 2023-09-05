package com.example.visitarad;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@RunWith(AndroidJUnit4.class)
public class LogInTest {

    @Rule
    public ActivityScenarioRule<MainActivityLogin> activityRule = new ActivityScenarioRule<>(MainActivityLogin.class);

    @Before
    public void setUp() {
        init(); // Inițializează Intents
    }

    @After
    public void cleanUp() {
        release(); // Eliberează Intents
    }

    @Test
    public void testUserSiParolaValide()
    {
        onView(withId(R.id.txtEmail)).perform(typeText("mirza_robert@yahoo.com"));
        onView(withId(R.id.txtPass)).perform(typeText("licenta"));

        closeSoftKeyboard();

        onView(withId(R.id.lgnbutton)).perform(click());
    }

    @Test
    public void testAccesareActivitateCreareCont(){
        // Apasa butonul de Creare Cont
        onView(withId(R.id.butonInregistrare)).perform(click());

        // Verifică dacă intenția pentru a deschide CreareCont activity a fost trimisă
        intended(hasComponent(CreareCont.class.getName()));
    }

    @Test
    public void testContinuareFaraCont(){
        onView(withId(R.id.textViewFaraCont)).perform(click());
        intended(hasComponent(PaginaPrincipala.class.getName()));
    }







}
