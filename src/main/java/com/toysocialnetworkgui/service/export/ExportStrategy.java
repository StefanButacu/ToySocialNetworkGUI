package com.toysocialnetworkgui.service.export;

import com.toysocialnetworkgui.utils.UserFriendDTO;
import com.toysocialnetworkgui.utils.UserMessageDTO;

import java.io.IOException;
import java.util.List;

public interface ExportStrategy {

    void export(List<UserFriendDTO> friends, List<UserMessageDTO> messages, String filePath) throws IOException;
}
