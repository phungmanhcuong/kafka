/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kafka.common;

public class ClientIdAndBroker implements ClientIdBroker {
    private static final String TO_STRING_FORMAT = "%s-%s-%d";
    public String clientId;
    public String brokerHost;
    public int brokerPort;

    public ClientIdAndBroker(String clientId, String brokerHost, int brokerPort) {
        this.clientId = clientId;
        this.brokerHost = brokerHost;
        this.brokerPort = brokerPort;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, clientId, brokerHost, brokerPort);
    }
}
