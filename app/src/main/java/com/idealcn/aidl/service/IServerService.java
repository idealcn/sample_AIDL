package com.idealcn.aidl.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.idealcn.aidl.Book;
import com.idealcn.aidl.IMyAidlInterface;

/**
 * author:idealgn
 * date:16-10-7 下午3:18
 */
public class IServerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    private Book book;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
        @Override
        public void process(Book book) throws RemoteException {
            System.out.println("book name---"+book.getName());
        }
    };
}
