package pl.edu.agh.util;

import com.github.dockerjava.api.model.EventStreamItem;
import com.github.dockerjava.api.model.PullEventStreamItem;
import com.github.dockerjava.core.command.EventStreamReader;

public interface RunnableTaskWithReturn {
    public EventStreamReader<EventStreamItem> run() throws Exception;
}
