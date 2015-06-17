package pl.edu.agh.model;

import java.util.Map;

public class DomainInfo {
    private int domainId;
    private int vcpuCount;
    private int maxCpu;
    private int maxMemory;
    private int vncPort;
    private int memory;

    public DomainInfo(int domainId, int vcpuCount, int maxCpu, int maxMemory, int vncPort, int memory) {
        this.domainId = domainId;
        this.vcpuCount = vcpuCount;
        this.maxCpu = maxCpu;
        this.maxMemory = maxMemory;
        this.vncPort = vncPort;
        this.memory = memory;
    }

    public int getDomainId() {
        return domainId;
    }

    public int getVcpuCount() {
        return vcpuCount;
    }

    public int getMaxCpu() {
        return maxCpu;
    }

    public int getMaxMemory() {
        return maxMemory;
    }

    public int getVncPort() {
        return vncPort;
    }

    public int getMemory() {
        return memory;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainInfo that = (DomainInfo) o;

        if (domainId != that.domainId) return false;
        if (vcpuCount != that.vcpuCount) return false;
        if (maxCpu != that.maxCpu) return false;
        if (maxMemory != that.maxMemory) return false;
        if (vncPort != that.vncPort) return false;
        if (memory != that.memory) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = domainId;
        result = 31 * result + vcpuCount;
        result = 31 * result + maxCpu;
        result = 31 * result + maxMemory;
        result = 31 * result + vncPort;
        result = 31 * result + memory;
        return result;
    }

}
