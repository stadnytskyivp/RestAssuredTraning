package dataProvider;

import org.testng.annotations.DataProvider;

public class Books {

    @DataProvider(name="BooksData")
    public Object[][] getBooks() {

        return new Object[][] {{"witcher","1"}, {"witcher","2"}, {"witcher","3"}};

    }

}
