/**
 * Copyright 2017 Institute of Computing Technology, Chinese Academy of Sciences.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms
 */
package eml.studio.shared.graph;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * Dataset node in Oozie workflow graph
 */
public class OozieDatasetNode extends OozieNode implements IsSerializable {

    /**
     * File id
     */
    private String file = "";

    public OozieDatasetNode() {
        super();
    }

    public void init(String widgetId, String datasetId, String filePath,
                     int x, int y) {
        init(widgetId, datasetId, x, y);
        file = filePath;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Generate a XML String for the node
     */
    @Override
    public String toXML() {
        StringBuffer sb = new StringBuffer(100);
        sb.append("<widget type='dataset'>\n");
        sb.append("  <id>").append(id).append("</id>\n");
        sb.append("  <moduleId>").append(moduleId).append("</moduleId>\n");
        sb.append("  <x>").append(x).append("</x>\n");
        sb.append("  <y>").append(y).append("</y>\n");
        sb.append("  <file>").append(file).append("</file>\n");
        sb.append("</widget>\n");
        return sb.toString();
    }
}
