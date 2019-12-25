package ir.science.essay.logfeature.usecase;

import ir.science.essay.entities.User;

public interface LogInUseCase {
    User login(String username, String password);
}
