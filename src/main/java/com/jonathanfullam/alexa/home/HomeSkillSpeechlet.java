package com.jonathanfullam.alexa.home;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import org.joda.time.ReadableInstant;
import org.joda.time.Seconds;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jfullam on 5/19/16.
 */
public class HomeSkillSpeechlet implements Speechlet{

    private String bedTime = "19:30:00";

    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {

    }

    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        return null;
    }

    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        Intent intent = intentRequest.getIntent();

        if (intent.getName().equals("BirthdayIntent")) {
            return handleBirthdayIntent(intent);
        } else if (intent.getName().equals("BedtimeIntent")) {
            return handleBedtimeIntent();
        } else {
            throw new SpeechletException("invalid intent");
        }
    }

    private SpeechletResponse handleBedtimeIntent() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText("It's way past your bedtime.  Go to bed now so you aren't too tired tomorrow.");
        return SpeechletResponse.newTellResponse(speech);
    }

    private SpeechletResponse handleBirthdayIntent(Intent intent) {
        Slot familyMember = intent.getSlot("FamilyMember");

        if (familyMember.getValue().equals("Derek")) {
            return getBirthdayResponse("May twelfth");
        } else if (familyMember.getValue().equals("Alex")) {
            return getBirthdayResponse("March thirty first");
        } else if (familyMember.getValue().equals("Zoey")) {
            return getBirthdayResponse("March eighth");
        } else if (familyMember.getValue().equals("Daddy") || familyMember.getValue().equals("Jonathan")) {
            return getBirthdayResponse("March twenty fifth");
        } else if (familyMember.getValue().equals("Mommy") || familyMember.getValue().equals("Michele")) {
            return getBirthdayResponse("November twenty second");
        } else if (familyMember.getValue().equals("Toby")) {
            return getBirthdayResponse("Who cares because he has a very stinky butt with poop on it");
        } else {
            return getBirthdayResponse("Only daddy knows that");
        }
    }

    private SpeechletResponse getBirthdayResponse(String response) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(response);
        return SpeechletResponse.newTellResponse(speech);
    }

    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

    }
}
