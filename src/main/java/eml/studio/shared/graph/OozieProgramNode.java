/**
 * Copyright 2017 Institute of Computing Technology, Chinese Academy of Sciences.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms
 */
package eml.studio.shared.graph;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

import eml.studio.client.ui.widget.program.ProgramWidget;

/**
 * Program node in Oozie workflow graph
 */
public class OozieProgramNode extends OozieNode implements IsSerializable {

    /**
     * Output files of the program
     */
    protected List<String> files = new LinkedList<String>();

    /**
     * Nickname of the input files
     */
    protected List<String> input_aliases = new LinkedList<String>();

    /**
     * Nickname of the output files
     */
    protected List<String> output_aliases = new LinkedList<String>();

    /**
     * Input Parameters of the program
     */
    protected List<String> params = new LinkedList<String>();

    protected boolean distributed = false;
    private boolean standaloneScript = false;

    private String workPath = null;
    private String cmdLine = null;
    private String script = null;
    private int inputFileCount = 0;
    private int outputFileCount = 0;

    private String oozJobId = ProgramWidget.Model.LATEST_OOZIE_JOBID;

    public OozieProgramNode() {
    }

    public void init(String widgetId, String moduleId, String workPath, int x, int y,
                     String oozJobId, int inCnt, int outCnt, boolean isDistributed) {
        init(widgetId, moduleId, x, y);
        setInputFileCount(inCnt);
        setOutputFileCount(outCnt);
        setOozJobId(oozJobId);
        this.setWorkPath(workPath);
        this.distributed = isDistributed;
    }

    public void initAsScriptNode(List<String> outFileIdList,
                                 String script) {
        files.addAll(outFileIdList);
        this.script = script;
        this.standaloneScript = true;
    }

    public void initAsCommonNode(List<String> outFileIdList, List<String> paramList) {
        files.addAll(outFileIdList);
        params.addAll(paramList);
    }

    public void initAsSqlNode(List<String> outFileIdList,
                              List<String> dyOutFileIdList,
                              List<String> paramList,
                              List<String> input_aliases,
                              List<String> output_aliases,
                              List<String> dy_input_aliases,
                              List<String> dy_output_aliases,
                              String script) {
        files.addAll(outFileIdList);
        this.input_aliases.addAll(input_aliases);
        this.output_aliases.addAll(output_aliases);
        setScript(script);
    }

    public List<String> getFiles() {
        return files;
    }

    public List<String> getParams() {
        return params;
    }


    public String getOozJobId() {
        return oozJobId;
    }

    public void setOozJobId(String oozJobId) {
        this.oozJobId = oozJobId;
    }

    public void addFile(String file) {
        files.add(file);
    }

    public void addParam(String param) {
        params.add(param);
    }

    public void addInputAliases(String aliases) {
        input_aliases.add(aliases);
    }

    public void addOutputAliases(String aliases) {
        output_aliases.add(aliases);
    }

    /**
     * Generate a XML String for the node
     */
    @Override
    public String toXML() {
        StringBuffer sb = new StringBuffer(500);
        sb.append("<widget type='program'>\n");
        genXML(sb);
        return sb.toString();
    }

    public void genXML(StringBuffer sb) {
        sb.append("  <id>").append(id).append("</id>\n");
        sb.append("  <moduleId>").append(moduleId).append("</moduleId>\n");
        sb.append("  <oozJob>").append(getOozJobId()).append("</oozJob>\n");
        sb.append("  <x>").append(x).append("</x>\n");
        sb.append("  <y>").append(y).append("</y>\n");
        sb.append("  <work_path>").append(String.valueOf(getWorkPath())).append("</work_path>\n");
        for (String file : files)
            sb.append("  <file>").append(file).append("</file>\n");
        for (String param : params)
            sb.append("  <param>").append(param).append("</param>\n");
        for (String aliases : input_aliases)
            sb.append("  <input_aliases>").append(aliases).append("</input_aliases>\n");
        for (String aliases : output_aliases)
            sb.append("  <output_aliases>").append(aliases).append("</output_aliases>\n");

        if (getScript() != null) {
            sb.append("  <incount>").append(getInputFileCount()).append("</incount>\n");
            sb.append("  <outcount>").append(getOutputFileCount()).append("</outcount>\n");

            String script_trans = getScript().replace(">", "&gt;").replace("<", "&lt;");
            sb.append("  <script>").append(script_trans).append("</script>\n");
        }
        sb.append("  <cmd_line>").append(String.valueOf(getCmdLine())).append("</cmd_line>\n");
        sb.append("  <is_distributed>").append(String.valueOf(isDistributed())).append("</is_distributed>\n");
        sb.append("  <is_standalone_script>").append(String.valueOf(standaloneScript)).append("</is_standalone_script>\n");
        sb.append("</widget>\n");
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public int getOutputFileCount() {
        return outputFileCount;
    }

    public void setOutputFileCount(int outputFileCount) {
        this.outputFileCount = outputFileCount;
    }

    public int getInputFileCount() {
        return inputFileCount;
    }

    public void setInputFileCount(int inputFileCount) {
        this.inputFileCount = inputFileCount;
    }

    public List<String> getOutputAliases() {
        return output_aliases;
    }

    public List<String> getInputAliases() {
        return input_aliases;
    }

    public boolean isDistributed() {
        return this.distributed;
    }

    public void setDistributed(boolean distributed) {
        this.distributed = distributed;
    }

    public String getCmdLine() {
        return cmdLine;
    }

    public void setCmdLine(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    public void setStandaloneScript(boolean standaloneScript) {
        this.standaloneScript = standaloneScript;
    }

    public String getWorkPath() {
        return workPath;
    }

    public void setWorkPath(String workPath) {
        this.workPath = workPath;
    }

}
