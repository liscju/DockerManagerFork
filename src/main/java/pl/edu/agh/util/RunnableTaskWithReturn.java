package pl.edu.agh.util;

import com.github.dockerjava.api.model.PullEventStreamItem;
import com.github.dockerjava.core.command.EventStreamReader;

public interface RunnableTaskWithReturn {
    public EventStreamReader<PullEventStreamItem> run() throws Exception;
}
