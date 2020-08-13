package dataProvider;

import org.testng.annotations.DataProvider;

public class Books {

    @DataProvider(name="BooksData")
    public Object[][] getBooks() {

        return new Object[][] {{"witcher","4"}, {"witcher","5"}, {"witcher","6"}};

    }

}
