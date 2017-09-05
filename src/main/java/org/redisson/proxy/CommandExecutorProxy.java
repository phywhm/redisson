/**
 * Copyright 2016 Nikita Koksharov
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
package org.redisson.proxy;

import org.redisson.command.CommandSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by PHY on 2017/9/1.
 */
public class CommandExecutorProxy implements InvocationHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandExecutorProxy.class);

    private CommandSyncService commandSyncService;

    public CommandExecutorProxy(CommandSyncService commandSyncService) {
        this.commandSyncService = commandSyncService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object res = method.invoke(commandSyncService, args);
        LOGGER.info("Redis command execute method {} cost {}ms", method.getName(), System.currentTimeMillis() - start);
        return res;
    }
}
