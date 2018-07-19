package ru.gcsales.seminar15.domain.executor;

import ru.gcsales.seminar15.domain.interactor.UseCase;

public interface Executor {

    void execute(UseCase useCase);
}
