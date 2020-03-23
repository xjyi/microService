package com.xianjinyi.gameProvider.deepclone;

import lombok.Cleanup;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: xianjinyi
 * @date 2020/01/02
 */
public class DeepClone {

   public  void toSeri() throws Exception{
       @Cleanup
       ByteArrayOutputStream bos = new ByteArrayOutputStream();
       @Cleanup
       ObjectOutputStream outputStream = new ObjectOutputStream(bos);
       outputStream.writeObject(this);

       // 反序列化
       @Cleanup
       ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
       @Cleanup
       ObjectInputStream reader = new ObjectInputStream(bis);
       Object o = reader.readObject();

   }
}
