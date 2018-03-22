minefieldsave: minefieldsave-test
minefieldsave-compile:
	javac -cp junit.jar -d . given/MineField.java given/MineFieldException.java given/MineFieldFileIO.java test/MineFieldFileIOTestField.java
minefieldsave-test: minefieldsave-compile
	java -cp hamcrest.jar:junit.jar:. org.junit.runner.JUnitCore MineFieldFileIOTestField

minefieldgame: minefieldgame-test
minefieldgame-compile:
	javac -cp junit.jar -d . given/MineField.java given/MineFieldException.java given/MineFieldFileIO.java test/MineFieldFileIOTestGame.java
minefieldgame-test: minefieldgame-compile
	java -cp hamcrest.jar:junit.jar:. org.junit.runner.JUnitCore MineFieldFileIOTestGame

clean:
	rm *.class
