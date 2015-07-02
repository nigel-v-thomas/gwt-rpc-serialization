/*
 * Copyright 2013 Xi CHEN
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wwarn.surveyor.util.client;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by: Xi
 */
public class GenericTestValue<T extends TestValue> implements IsSerializable{

  private T testValue;

  public GenericTestValue(){

  }

  public GenericTestValue(T testValue){
    this.testValue = testValue;
  }

  public T getTestValue(){
    return testValue;
  }

  public void setTestValue(T testValue){
    this.testValue = testValue;
  }

  @Override
  public boolean equals(Object o){
    if(this == o) return true;
    if(!(o instanceof GenericTestValue)) return false;

    GenericTestValue that = (GenericTestValue)o;

    if(!testValue.equals(that.testValue)) return false;

    return true;
  }

  @Override
  public int hashCode(){
    return testValue.hashCode();
  }
}
