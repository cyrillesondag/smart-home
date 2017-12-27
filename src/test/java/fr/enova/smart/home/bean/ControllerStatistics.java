package fr.enova.smart.home.bean;

public class ControllerStatistics {

    private int retries;

    public int getRetries() {
        return this.retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    private int readCnt;

    public int getReadCnt() {
        return this.readCnt;
    }

    public void setReadCnt(int readCnt) {
        this.readCnt = readCnt;
    }

    private int readAborts;

    public int getReadAborts() {
        return this.readAborts;
    }

    public void setReadAborts(int readAborts) {
        this.readAborts = readAborts;
    }

    private int routedbusy;

    public int getRoutedbusy() {
        return this.routedbusy;
    }

    public void setRoutedbusy(int routedbusy) {
        this.routedbusy = routedbusy;
    }

    private int ACKCnt;

    public int getACKCnt() {
        return this.ACKCnt;
    }

    public void setACKCnt(int ACKCnt) {
        this.ACKCnt = ACKCnt;
    }

    private int OOFCnt;

    public int getOOFCnt() {
        return this.OOFCnt;
    }

    public void setOOFCnt(int OOFCnt) {
        this.OOFCnt = OOFCnt;
    }

    private int noack;

    public int getNoack() {
        return this.noack;
    }

    public void setNoack(int noack) {
        this.noack = noack;
    }

    private int broadcastWriteCnt;

    public int getBroadcastWriteCnt() {
        return this.broadcastWriteCnt;
    }

    public void setBroadcastWriteCnt(int broadcastWriteCnt) {
        this.broadcastWriteCnt = broadcastWriteCnt;
    }

    private int callbacks;

    public int getCallbacks() {
        return this.callbacks;
    }

    public void setCallbacks(int callbacks) {
        this.callbacks = callbacks;
    }

    private int writeCnt;

    public int getWriteCnt() {
        return this.writeCnt;
    }

    public void setWriteCnt(int writeCnt) {
        this.writeCnt = writeCnt;
    }

    private int badChecksum;

    public int getBadChecksum() {
        return this.badChecksum;
    }

    public void setBadChecksum(int badChecksum) {
        this.badChecksum = badChecksum;
    }

    private int nondelivery;

    public int getNondelivery() {
        return this.nondelivery;
    }

    public void setNondelivery(int nondelivery) {
        this.nondelivery = nondelivery;
    }

    private int CANCnt;

    public int getCANCnt() {
        return this.CANCnt;
    }

    public void setCANCnt(int CANCnt) {
        this.CANCnt = CANCnt;
    }

    private int NAKCnt;

    public int getNAKCnt() {
        return this.NAKCnt;
    }

    public void setNAKCnt(int NAKCnt) {
        this.NAKCnt = NAKCnt;
    }

    private int netbusy;

    public int getNetbusy() {
        return this.netbusy;
    }

    public void setNetbusy(int netbusy) {
        this.netbusy = netbusy;
    }

    private int SOFCnt;

    public int getSOFCnt() {
        return this.SOFCnt;
    }

    public void setSOFCnt(int SOFCnt) {
        this.SOFCnt = SOFCnt;
    }

    private int broadcastReadCnt;

    public int getBroadcastReadCnt() {
        return this.broadcastReadCnt;
    }

    public void setBroadcastReadCnt(int broadcastReadCnt) {
        this.broadcastReadCnt = broadcastReadCnt;
    }

    private int badroutes;

    public int getBadroutes() {
        return this.badroutes;
    }

    public void setBadroutes(int badroutes) {
        this.badroutes = badroutes;
    }

    private int ACKWaiting;

    public int getACKWaiting() {
        return this.ACKWaiting;
    }

    public void setACKWaiting(int ACKWaiting) {
        this.ACKWaiting = ACKWaiting;
    }

    private int dropped;

    public int getDropped() {
        return this.dropped;
    }

    public void setDropped(int dropped) {
        this.dropped = dropped;
    }
}