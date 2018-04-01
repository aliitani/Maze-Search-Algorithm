package com.company;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class GenerateMazes {

    public GenerateMazes() {

    }

    public void generateMazes(){
        BufferedWriter bwSmall = null;
        FileWriter fwSmall = null;

        BufferedWriter bwBig = null;
        FileWriter fwBig = null;

        BufferedWriter bwMedium = null;
        FileWriter fwMedium = null;

        try {

            fwSmall = new FileWriter("mazes/smallMaze");
            bwSmall = new BufferedWriter(fwSmall);
            bwSmall.write(
                    "####################\n" +
                        "#.#           #   ##\n" +
                        "#     # # # #    # #\n" +
                        "# ##  #      ## ## #\n" +
                        "# # #   # # # #    #\n" +
                        "#   # # # #  #    ##\n" +
                        "# # #   #         P#\n" +
                        "####################");

            fwMedium = new FileWriter("mazes/mediumMaze");
            bwMedium = new BufferedWriter(fwMedium);
            bwMedium.write(
                    "##################################\n" +
                        "#   #   # # #   # #   # #      # #\n" +
                        "## # # ## # # # #  # #   # ## # ##\n" +
                        "# #           #   #   # #      # #\n" +
                        "## # ## ## # # # #  #    #  ## ###\n" +
                        "#       #   #     # # #      #   #\n" +
                        "# # # ## ## # # # # #  # # ##  # #\n" +
                        "# #   #     # #      #       #   #\n" +
                        "#  # ## # #  # ### #   ## #  ## ##\n" +
                        "# # #                #     #     #\n" +
                        "## # ## ## ### ## ### # # # # ## #\n" +
                        "# #         #  #         #   #   #\n" +
                        "# #   # # ####  # ## ## ## #   # #\n" +
                        "#.  ##     #   #       # #   # #P#\n" +
                        "##################################\n");

            fwBig = new FileWriter("mazes/bigMaze");
            bwBig = new BufferedWriter(fwBig);
            bwBig.write(
                    "###################################\n" +
                        "## # # ## # # # #  # #   # ## # # #\n" +
                        "# #           #   #   # #         #\n" +
                        "##.# ## ## # # # #  #    # # # ## #\n" +
                        "#       #   #     # # # # #     # #\n" +
                        "# # # ## ## # # # # ## #  # # ##  #\n" +
                        "# #   #     # #         #     # # #\n" +
                        "#  # ## # #  # # #### #   ## #  ###\n" +
                        "# # #       # #         #     #   #\n" +
                        "## # ## # # # # # ## ### # # # # ##\n" +
                        "# #   # #         #         #   # #\n" +
                        "# # #    # # # ##  # ## ## ## #   #\n" +
                        "#     # #       # #             # #\n" +
                        "# # ## # ## # #   # ## # # # # ####\n" +
                        "#   #   # # # # #   # # #         #\n" +
                        "# #  ##  ##       #    # # ### ####\n" +
                        "#   #   #   # # #   # #   #       #\n" +
                        "# #  # # ##   # # ### # ## # # ## #\n" +
                        "# # #       # #   #     #     # # #\n" +
                        "# ## # # # ##  ##  ## # ## # # #  #\n" +
                        "# #       # # #   #   #         # #\n" +
                        "##  # ###    ### ## #  ### ### ## #\n" +
                        "# #       # #   # #   #           #\n" +
                        "# # # # ## ## # #   # # ## # # ## #\n" +
                        "#   #         #   # #   #       # #\n" +
                        "###  # # ## #   # # # ##  # # #   #\n" +
                        "# # # #       #   #   # # #     # #\n" +
                        "##     ### # #  #   #   #   ## # ##\n" +
                        "# # # #       #   #   #   #       #\n" +
                        "# #  #  # ## #  # # ##### # # ## ##\n" +
                        "#   # #   #   #   #   # #   #     #\n" +
                        "## # #  #  # #  #   #     # # # # #\n" +
                        "#   # #   #   #   # # # #   #     #\n" +
                        "###    # # ## ## #  # ### #   # # #\n" +
                        "#   # # #     #   #       # #   #P#\n" +
                        "###################################\n");
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bwSmall != null){
                    bwSmall.close();
                }
                if(fwSmall != null) {
                    fwSmall.close();
                }
                if(bwMedium != null) {
                    bwMedium.close();
                }
                if(fwMedium != null) {
                    fwMedium.close();
                }
                if(bwBig != null) {
                    bwBig.close();
                }
                if(fwBig != null) {
                    fwBig.close();
                }
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
