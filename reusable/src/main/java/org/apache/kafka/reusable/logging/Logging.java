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

package org.apache.kafka.reusable.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Optional;

public interface Logging {
    default String logIdent() {
        return null;
    }

    default String msgWithLogIdent(String message) {
        return Optional.ofNullable(logIdent())
                .map(e -> logIdent() + message)
                .orElse(message);
    }

    Marker FATAL_MARKER = MarkerFactory.getMarker("FATAL");

    default Logger logger() {
        return LoggerFactory.getLogger(getClass());
    }

    default boolean isTraceEnabled() {
        return logger().isTraceEnabled();
    }

    default boolean isDebugEnabled() {
        return logger().isDebugEnabled();
    }

    default void error(String message) {
        logger().error(msgWithLogIdent(message));
    }

    default void error(String message, Throwable throwable) {
        logger().error(msgWithLogIdent(message), throwable);
    }

    default void trace(String message) {
        logger().error(msgWithLogIdent(message));
    }

    default void trace(String message, Throwable throwable) {
        logger().error(msgWithLogIdent(message), throwable);
    }

    default void warn(String message) {
        logger().error(msgWithLogIdent(message));
    }

    default void warn(String message, Throwable throwable) {
        logger().error(msgWithLogIdent(message), throwable);
    }

    default void debug(String message) {
        logger().error(msgWithLogIdent(message));
    }

    default void debug(String message, Throwable throwable) {
        logger().error(msgWithLogIdent(message), throwable);
    }

    default void info(String message) {
        logger().error(msgWithLogIdent(message));
    }

    default void info(String message, Throwable throwable) {
        logger().error(msgWithLogIdent(message), throwable);
    }

    default void fatal(String message) {
        logger().error(FATAL_MARKER, msgWithLogIdent(message));
    }

    default void fatal(String message, Throwable throwable) {
        logger().error(FATAL_MARKER, msgWithLogIdent(message), throwable);
    }
}
