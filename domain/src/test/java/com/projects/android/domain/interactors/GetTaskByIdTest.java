package com.projects.android.domain.interactors;

import com.projects.android.domain.executor.ThreadExecutor;
import com.projects.android.domain.executor.PostExecutionThread;
import com.projects.android.domain.interactors.impl.GetTaskByIdInteractorImpObservable;
import com.projects.android.domain.model.Task;
import com.projects.android.domain.repository.TaskRepository;
import com.projects.android.domain.threading.TestPostExecutionThread;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import java.util.Date;


public class GetTaskByIdTest {

    private PostExecutionThread mPostExecutionThread;
    @Mock
    private ThreadExecutor mMockThreadExecutor;
    @Mock
    private TaskRepository mMockTaskRepository;
    @Mock
    private GetTaskByIdInteractor.Callback mMockCallback;

    private long mTaskId;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        mPostExecutionThread = new TestPostExecutionThread();
        mTaskId = 3;
    }

    @Test
    public void taskShouldNotBeFoundTest() throws Exception{
        GetTaskByIdInteractorImpObservable testInteractor = new GetTaskByIdInteractorImpObservable(mMockThreadExecutor, mPostExecutionThread, mMockCallback, mMockTaskRepository,
                mTaskId);
        testInteractor.run();
        Mockito.verify(mMockTaskRepository).getTaskById(mTaskId);
        Mockito.verifyNoMoreInteractions(mMockTaskRepository);
        Mockito.verify(mMockCallback).noTaskFound();
    }

    @Test
    public void taskShouldBeFoundTest() throws Exception{
        Task dummyTask = new Task("Test Title", 1, new Date(), true);
        when(mMockTaskRepository.getTaskById(mTaskId)).thenReturn(dummyTask);
        GetTaskByIdInteractorImpObservable testInteractor = new GetTaskByIdInteractorImpObservable(mMockThreadExecutor, mPostExecutionThread, mMockCallback, mMockTaskRepository,
                mTaskId);
        testInteractor.run();
        Mockito.verify(mMockTaskRepository).getTaskById(mTaskId);
        Mockito.verifyNoMoreInteractions(mMockTaskRepository);
        Mockito.verify(mMockCallback).onTaskRetrieved(dummyTask);
    }


}
