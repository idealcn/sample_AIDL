// IMyAidlInterface.aidl
package com.idealcn.aidl;
import com.idealcn.aidl.Book;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    void process(in Book book);
}
