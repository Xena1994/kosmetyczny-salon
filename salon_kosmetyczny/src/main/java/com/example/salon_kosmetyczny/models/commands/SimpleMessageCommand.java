package com.example.salon_kosmetyczny.models.commands;

//import edu.uph.platformy.uphgabinet.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMessageCommand {
   // @NotNull
    //private User to;
    @NotNull
    private String subject;
    @NotNull
    @Size(max = 300)
    private String text;
}
