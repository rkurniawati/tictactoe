package edu.wsu.model;

public class ModelSingleton {
  private static TicTacToeGame model;

  public static TicTacToeGame getInstance() {
    if (model == null) {
      model = new TicTacToeImplementation();
    }
    return model;
  }

}
