package com.epam.stockparser.parser;

import java.io.IOException;
import java.util.List;

public interface CustomFileParser<T> {

    List<T> parseFile() throws IOException;
}
