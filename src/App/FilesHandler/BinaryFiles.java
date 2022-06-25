package App.FilesHandler;

import App.Exceptions.MissMatchClassException;

import java.io.IOException;
import java.util.ArrayList;

public interface BinaryFiles
{
    void writeBinary(String fileName, Object content) throws IOException, MissMatchClassException;
    ArrayList readBinary(String fileName) throws IOException;
    void deleteBinary(String fileName) throws IOException;
}
