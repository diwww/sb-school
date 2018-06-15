package ru.gcsales.seminar4;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

public class MyIntentService extends IntentService {
    private StateManager stateManager = StateManager.getInstance();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Get newly generated state from incoming intent
        StateManager.State[] states = StateManager.State.values();
        int number = intent.getIntExtra(Constants.NUMBER_EXTRA, 0) % states.length;
        // Set this state in stateManager
        stateManager.setState(states[number]);
        // Create a broadcastIntent
        Intent broadcastIntent = new Intent(Constants.FILTER);
        // Add new state extra to intent
        broadcastIntent.putExtra(Constants.STATE_EXTRA, stateManager.getState().toString());
        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        // TODO: why does not work with permissions?
        // Broadcast intent
        sendOrderedBroadcast(broadcastIntent, null);
    }

    public static Intent newIntent(Context context, int num) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.putExtra(Constants.NUMBER_EXTRA, num);
        return intent;
    }
}
