/**
 * Copyright 2014 Nikita Koksharov, Nickolay Borbit
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
package org.redisson.command;

import java.net.InetSocketAddress;
import java.util.List;

import org.reactivestreams.Publisher;
import org.redisson.SlotCallback;
import org.redisson.client.codec.Codec;
import org.redisson.client.protocol.RedisCommand;
import org.redisson.connection.ConnectionManager;

/**
 *
 * @author Nikita Koksharov
 *
 */
public interface CommandReactiveExecutor {

    ConnectionManager getConnectionManager();

    <T> Publisher<Void> writeAllObservable(RedisCommand<T> command, Object ... params);

    <R, T> Publisher<R> writeAllObservable(RedisCommand<T> command, SlotCallback<T, R> callback, Object ... params);

    <T, R> Publisher<R> readObservable(InetSocketAddress client, String key, Codec codec, RedisCommand<T> command, Object ... params);

    <T, R> Publisher<R> evalWriteObservable(String key, Codec codec, RedisCommand<T> evalCommandType, String script, List<Object> keys, Object... params);

    <T, R> Publisher<R> evalReadObservable(String key, Codec codec, RedisCommand<T> evalCommandType, String script, List<Object> keys, Object ... params);

    <T, R> Publisher<R> writeObservable(String key, RedisCommand<T> command, Object ... params);

    <T, R> Publisher<R> writeObservable(String key, Codec codec, RedisCommand<T> command, Object ... params);

    <T, R> Publisher<R> readObservable(String key, RedisCommand<T> command, Object ... params);

    <T, R> Publisher<R> readObservable(String key, Codec codec, RedisCommand<T> command, Object ... params);

}