package com.github.dskprt.patchr.main;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

public class Main {

    public static void main(String[] args) {
        CommandPatch cp = new CommandPatch();
        CommandGenerate cg = new CommandGenerate();

        JCommander jc = JCommander.newBuilder()
                .addCommand("patch", cp)
                .addCommand("generate", cg, "gen")
                .build();

        jc.parse(args);

        if(jc.getParsedCommand() == null) {
            jc.usage();
            return;
        }

        switch(jc.getParsedCommand()) {
            case "patch":
                System.out.println("jar: " + cp.jar);
                System.out.println("patch: " + cp.patch);
                break;
            case "generate":
                System.out.println("original: " + cg.original);
                System.out.println("mod: " + cg.modified);

                if(cg.output != null) {
                    System.out.println("out: " + cg.output);
                }
                break;
        }
    }

    @Parameters(commandDescription = "Patch existing JAR.")
    private static class CommandPatch {

        @Parameter(names = "--jar", description = "JAR to patch", required = true)
        private String jar;

        @Parameter(names = "--patch", description = "Patch file", required = true)
        private String patch;
    }

    @Parameters(commandDescription = "Generate patch file from JAR.")
    private static class CommandGenerate {

        @Parameter(names = "--original", description = "Original JAR", required = true)
        private String original;

        @Parameter(names = "--modified", description = "Modified JAR", required = true)
        private String modified;

        @Parameter(names = "--output", description = "Output file")
        private String output;
    }
}
