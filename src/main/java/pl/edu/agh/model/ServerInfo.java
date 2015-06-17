package pl.edu.agh.model;

import java.util.Map;

public class ServerInfo {
    private String runningDefinedDomains;
    private int memory;
    private int cores;
    private String hostname;

    public ServerInfo(String runningDefinedDomains, int memory, int cores, String hostname) {
        this.runningDefinedDomains = runningDefinedDomains;
        this.memory = memory;
        this.cores = cores;
        this.hostname = hostname;
    }

    public String getRunningDefinedDomains() {
        return runningDefinedDomains;
    }

    public int getMemory() {
        return memory;
    }

    public int getCores() {
        return cores;
    }

    public String getHostname() {
        return hostname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerInfo that = (ServerInfo) o;

        if (memory != that.memory) return false;
        if (cores != that.cores) return false;
        if (runningDefinedDomains != null ? !runningDefinedDomains.equals(that.runningDefinedDomains) : that.runningDefinedDomains != null)
            return false;
        if (hostname != null ? !hostname.equals(that.hostname) : that.hostname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = runningDefinedDomains != null ? runningDefinedDomains.hashCode() : 0;
        result = 31 * result + memory;
        result = 31 * result + cores;
        result = 31 * result + (hostname != null ? hostname.hashCode() : 0);
        return result;
    }

    public static ServerInfo createFromProperties(Map<String, String> properties) {
        String runningDefinedDomains = properties.get("Running / defined domains");
        int memory = Integer.parseInt(properties.get("Memory"));
        int cores = Integer.parseInt(properties.get("Cores"));
        String hostname = properties.get("Hostname");

        return new ServerInfo(runningDefinedDomains,memory,cores,hostname);
    }
}
