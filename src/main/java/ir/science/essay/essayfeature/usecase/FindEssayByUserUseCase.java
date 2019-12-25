package ir.science.essay.essayfeature.usecase;

import ir.science.essay.entities.Article;

import java.util.List;

public interface FindEssayByUserUseCase {
    List<Article> get();
    Article get(int id);
}
