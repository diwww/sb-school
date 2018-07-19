package ru.gcsales.seminar11;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements OnActivityCallback {

    private Toolbar mToolbar;
    private IMyAidlInterface mIMyAidlInterface;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_slot, ReadFragment.newInstance(MainActivity.this, null))
                    .commit();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIMyAidlInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(MyService.newIntent(this), mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(mServiceConnection);
        mIMyAidlInterface = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void init() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_slot, WriteFragment.newInstance(this, null))
                    .commit();
        }

        return true;
    }

    @Override
    public String getData() throws RemoteException {
        return mIMyAidlInterface.getText();
    }

    @Override
    public void setData(String data) throws RemoteException {
        mIMyAidlInterface.setText(data);
    }
}
