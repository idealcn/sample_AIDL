package com.idealcn.ipc.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.idealcn.aidl.Book;
import com.idealcn.aidl.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface iMyAidlInterface;
    private boolean bound;

    private Button mBtnProcess;
    private EditText mInputText;
    private Button mBtnBind;
    private Button mBtnUnbind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnProcess = (Button) findViewById(R.id.process);
        mInputText = (EditText) findViewById(R.id.input);
        mBtnBind = (Button) findViewById(R.id.bind);
        mBtnUnbind = (Button) findViewById(R.id.unbind);





        mBtnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bound) {
                    Book book = new Book();
                    book.setName(mInputText.getText().toString().trim());
                    try {
                        iMyAidlInterface.process(book);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        mBtnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bound){
                    Intent intent = new Intent();
                    intent.setAction("com.idealcn.aidl");
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setPackage("com.idealcn.aidl");
                    bindService(intent,connection,BIND_AUTO_CREATE);
                }
            }
        });

        mBtnUnbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bound) {
                    unbindService(connection);
                    bound =false;
                }
            }
        });

    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);

            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            bound = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bound) {
            unbindService(connection);
            bound =false;
        }
    }
}
