package ir.science.essay.essayfeature.usecase;

import ir.science.essay.entities.Article;

public interface EditEssayByUserUseCase {
    void editInformation(Article article,int id);
    void editPublish(int articleId);
}
