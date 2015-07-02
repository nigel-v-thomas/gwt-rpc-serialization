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

package org.wwarn.surveyor.util.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.wwarn.surveyor.util.shared.RpcTestMapKey;
import org.wwarn.surveyor.util.shared.RpcTestMapValue;
import org.wwarn.surveyor.util.shared.RpcTestValue;

import java.util.List;
import java.util.Map;

/**
 * Created by: Xi
 */
@RemoteServiceRelativePath("TestService")
public interface TestService extends RemoteService {

  RpcTestValue getRpcTestValue();

  List<RpcTestValue> getRpcTestValueList();

  Map<RpcTestMapKey, RpcTestMapValue> getRpcTestValueStringMap();

}
