// IMyAidlInterface.aidl
package com.idealcn.aidl;
import com.idealcn.aidl.Book;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    //in 是个标记,在自定义的对象类型前2必须注明in或者out或者inout
    //否则,无法生成aidl
    void process(in Book book);
}
