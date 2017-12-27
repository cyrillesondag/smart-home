package fr.enova.smart.home.bean;

public class Result {

    private String neighbors;

    public String getNeighbors() {
        return this.neighbors;
    }

    public void setNeighbors(String neighbors) {
        this.neighbors = neighbors;
    }

    private boolean isReady;

    public boolean getIsReady() {
        return this.isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    private int awakedDelay;

    public int getAwakedDelay() {
        return this.awakedDelay;
    }

    public void setAwakedDelay(int awakedDelay) {
        this.awakedDelay = awakedDelay;
    }

    private String OpenZwaveLibraryVersion;

    public String getOpenZwaveLibraryVersion() {
        return this.OpenZwaveLibraryVersion;
    }

    public void setOpenZwaveLibraryVersion(String OpenZwaveLibraryVersion) {
        this.OpenZwaveLibraryVersion = OpenZwaveLibraryVersion;
    }

    private boolean isPrimaryController;

    public boolean getIsPrimaryController() {
        return this.isPrimaryController;
    }

    public void setIsPrimaryController(boolean isPrimaryController) {
        this.isPrimaryController = isPrimaryController;
    }

    private int pollInterval;

    public int getPollInterval() {
        return this.pollInterval;
    }

    public void setPollInterval(int pollInterval) {
        this.pollInterval = pollInterval;
    }

    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private ControllerStatistics controllerStatistics;

    public ControllerStatistics getControllerStatistics() {
        return this.controllerStatistics;
    }

    public void setControllerStatistics(ControllerStatistics controllerStatistics) {
        this.controllerStatistics = controllerStatistics;
    }

    private boolean isBusy;

    public boolean getIsBusy() {
        return this.isBusy;
    }

    public void setIsBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }

    private int outgoingSendQueue;

    public int getOutgoingSendQueue() {
        return this.outgoingSendQueue;
    }

    public void setOutgoingSendQueue(int outgoingSendQueue) {
        this.outgoingSendQueue = outgoingSendQueue;
    }

    private String controllerCapabilities;

    public String getControllerCapabilities() {
        return this.controllerCapabilities;
    }

    public void setControllerCapabilities(String controllerCapabilities) {
        this.controllerCapabilities = controllerCapabilities;
    }

    private boolean isStaticUpdateController;

    public boolean getIsStaticUpdateController() {
        return this.isStaticUpdateController;
    }

    public void setIsStaticUpdateController(boolean isStaticUpdateController) {
        this.isStaticUpdateController = isStaticUpdateController;
    }

    private String PythonOpenZwaveLibraryVersion;

    public String getPythonOpenZwaveLibraryVersion() {
        return this.PythonOpenZwaveLibraryVersion;
    }

    public void setPythonOpenZwaveLibraryVersion(String PythonOpenZwaveLibraryVersion) {
        this.PythonOpenZwaveLibraryVersion = PythonOpenZwaveLibraryVersion;
    }

    private int startTime;

    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    private String devicePath;

    public String getDevicePath() {
        return this.devicePath;
    }

    public void setDevicePath(String devicePath) {
        this.devicePath = devicePath;
    }

    private String controllerNodeCapabilities;

    public String getControllerNodeCapabilities() {
        return this.controllerNodeCapabilities;
    }

    public void setControllerNodeCapabilities(String controllerNodeCapabilities) {
        this.controllerNodeCapabilities = controllerNodeCapabilities;
    }

    private String stateDescription;

    public String getStateDescription() {
        return this.stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    private int nodesCount;

    public int getNodesCount() {
        return this.nodesCount;
    }

    public void setNodesCount(int nodesCount) {
        this.nodesCount = nodesCount;
    }

    private boolean isBridgeController;

    public boolean getIsBridgeController() {
        return this.isBridgeController;
    }

    public void setIsBridgeController(boolean isBridgeController) {
        this.isBridgeController = isBridgeController;
    }

    private int scenesCount;

    public int getScenesCount() {
        return this.scenesCount;
    }

    public void setScenesCount(int scenesCount) {
        this.scenesCount = scenesCount;
    }

    private int mode;

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    private int sleepingNodesCount;

    public int getSleepingNodesCount() {
        return this.sleepingNodesCount;
    }

    public void setSleepingNodesCount(int sleepingNodesCount) {
        this.sleepingNodesCount = sleepingNodesCount;
    }
}
