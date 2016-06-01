package com.jonathanfullam.alexa.home;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.jonathanfullam.alexa.home.com.jonathanfullam.alexa.home.bedtime.BedtimeIntentHandler;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jfullam on 5/19/16.
 */
@Component
public class HomeSkillSpeechlet implements Speechlet {

    @Autowired
    private BedtimeIntentHandler betimeIntentHandler;

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
            return betimeIntentHandler.onIntent();
        } else {
            throw new SpeechletException("invalid intent");
        }
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
