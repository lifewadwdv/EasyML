/**
 * Copyright 2017 Institute of Computing Technology, Chinese Academy of Sciences.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms
 */
package eml.studio.shared.util;

import eml.studio.shared.oozie.OozieAction;

/**
 * ProgramUtil methods in order to distinguish the elements in program modules
 */
public class ProgramUtil {

    /**
     * @return if is distributed
     */
    public static boolean isDistributed(String type) {
        return "分布式".equals(type) || "distributed".equals(type)
                || "spark".equals(type)
                || "mapreduce".equals(type);
    }

    public static boolean isETL(String type) {
        return "ETL".equals(type) || "etl".equals(type);
    }

    public static boolean isStandalone(String type) {
        return "单机".equals(type)
                || "standalone".equals(type)
                || "Standalone".equals(type)
                || "STANDALONE".equals(type);
    }

    public static boolean isTensorflow(String type) {
        return "Tensorflow".equals(type) || "tensorflow".equals(type);
    }

    public static boolean isSuccess(OozieAction action) {
        if (action == null ||
                action.getAppPath() == null ||
                "".equals(action.getAppPath())) return false;
        return "OK".equals(action.getStatus()) || "SUCCESS".equals(action.getStatus());
    }


    public static boolean isOkState(OozieAction action) {
        return "OK".equals(action.getStatus());
    }

    public static boolean isErrorState(OozieAction action) {
        return "ERROR".equals(action.getStatus())
                || "FAILED".equals(action.getStatus())
                || "KILLED".equals(action.getStatus());
    }

    public static boolean isNewState(OozieAction action) {
        return "new".equals(action.getStatus());
    }
}
