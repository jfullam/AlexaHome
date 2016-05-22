package com.jonathanfullam.alexa.home;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import org.joda.time.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jfullam on 5/19/16.
 */
@Component
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

        DateTime now = DateTime.now(DateTimeZone.forID("America/New_York"));
        DateTime bedtime = now.withTime(19, 30, 0, 0);



        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();

        if (now.isAfter(bedtime)) {
            speech.setText("It's past your bedtime!  Make sure you cleaned up any messes you made and then go to bed!");
            return SpeechletResponse.newTellResponse(speech);
        } else {
            int hours = Minutes.minutesBetween(now, bedtime).getValue(0) / 60;
            int minutes = Minutes.minutesBetween(now, bedtime).getValue(0) % 60;
            String bedtimeStr = "Your bedtime is in " + hours + " hours and " + minutes + " minutes.";

            if (hours > 8) {
                bedtimeStr = bedtimeStr + " Did you seriously ask me that?  It's still the morning!";
            }
            else if (hours > 2) {
                bedtimeStr = bedtimeStr + " You still have plenty of time.  Go and play.";
            } else {
                bedtimeStr = bedtimeStr + " It's not too far away so maybe you should start winding down.  Don't forget to clean up your toys before bed too.";
            }

            speech.setText(bedtimeStr);
            return SpeechletResponse.newTellResponse(speech);
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
