package sample;

public class Pentominoes {


    public static int[][][][][] data = {
            {//L
                    {//1
                            {
                                    {1, 0},
                                    {1, 0},
                                    {1, 0},
                                    {1, 1}
                            }
                    },
                    {
                            {
                                    {1, 1},
                                    {0, 1},
                                    {0, 1},
                                    {0, 1}
                            }
                    },
                    {
                            {
                                    {1, 1, 1, 1},
                                    {1, 0, 0, 0}
                            }
                    },
                    {
                            {
                                    {0, 0, 0, 1},
                                    {1, 1, 1, 1}
                            }
                    },
                    {
                            {
                                    {0, 1},
                                    {0, 1},
                                    {0, 1},
                                    {1, 1}
                            }
                    },
                    {
                            {
                                    {1, 1},
                                    {1, 0},
                                    {1, 0},
                                    {1, 0}
                            }
                    },
                    {
                            {
                                    {1, 0, 0, 0},
                                    {1, 1, 1, 1}
                            }
                    },
                    {
                            {
                                    {1, 1, 1, 1},
                                    {0, 0, 0, 1}
                            }
                    },
                    {
                            {
                                    {1},
                                    {1},
                                    {1},
                                    {1}
                            },
                            {
                                    {0},
                                    {0},
                                    {0},
                                    {1}
                            }
                    },
                    {
                            {
                                    {1},
                                    {1},
                                    {1},
                                    {1}
                            },
                            {
                                    {1},
                                    {0},
                                    {0},
                                    {0}
                            }
                    },
                    {
                            {
                                    {1, 1, 1, 1}
                            },
                            {
                                    {0, 0, 0, 1}
                            }
                    },
                    {
                            {
                                    {1, 1, 1, 1}
                            },
                            {
                                    {1, 0, 0, 0}
                            }
                    },
//
                    {
                            {
                                    {0},
                                    {0},
                                    {0},
                                    {1}
                            },
                            {
                                    {1},
                                    {1},
                                    {1},
                                    {1}
                            }
                    },
                    {

                            {
                                    {1},
                                    {0},
                                    {0},
                                    {0}
                            },
                            {
                                    {1},
                                    {1},
                                    {1},
                                    {1}
                            }
                    },
                    {
                            {
                                    {0, 0, 0, 1}
                            },
                            {
                                    {1, 1, 1, 1}
                            }
                    },
                    {
                            {
                                    {1, 0, 0, 0}
                            },
                            {
                                    {1, 1, 1, 1}
                            }
                    },
//
                    {
                            {
                                    {0, 1}
                            },
                            {
                                    {0, 1}
                            },
                            {
                                    {0, 1}
                            },
                            {
                                    {1, 1}
                            }
                    },
                    {
                            {
                                    {1, 0}
                            },
                            {
                                    {1, 0}
                            },
                            {
                                    {1, 0}
                            },
                            {
                                    {1, 1}
                            }
                    },
                    {
                            {
                                    {1, 1}
                            },
                            {
                                    {1, 0}
                            },
                            {
                                    {1, 0}
                            },
                            {
                                    {1, 0}
                            }
                    },
                    {
                            {
                                    {1, 1}
                            },
                            {
                                    {0, 1}
                            },
                            {
                                    {0, 1}
                            },
                            {
                                    {0, 1}
                            }
                    },
//
                    {
                            {
                                    {1},
                                    {0}
                            },
                            {
                                    {1},
                                    {0}
                            },
                            {
                                    {1},
                                    {0}
                            },
                            {
                                    {1},
                                    {1}
                            }
                    },
                    {
                            {
                                    {1},
                                    {1}
                            },
                            {
                                    {1},
                                    {0}
                            },
                            {
                                    {1},
                                    {0}
                            },
                            {
                                    {1},
                                    {0}
                            }
                    },
                    {
                            {
                                    {1},
                                    {1}
                            },
                            {
                                    {0},
                                    {1}
                            },
                            {
                                    {0},
                                    {1}
                            },
                            {
                                    {0},
                                    {1}
                            }
                    },
                    {
                            {
                                    {0},
                                    {1}
                            },
                            {
                                    {0},
                                    {1}
                            },
                            {
                                    {0},
                                    {1}
                            },
                            {
                                    {1},
                                    {1}
                            }
                    }

            },
            {//P
                    {//1
                            {
                                    {1,1},
                                    {1,1},
                                    {1,0}
                            }
                    },
                    {//2
                            {
                                    {1,1},
                                    {1,1},
                                    {0,1}
                            }
                    },
                    {//3
                            {
                                    {0,1},
                                    {1,1},
                                    {1,1}
                            }
                    },
                    {//4
                            {
                                    {1,0},
                                    {1,1},
                                    {1,1}
                            }
                    },
                    {//5
                            {
                                    {0,1,1},
                                    {1,1,1}
                            }
                    },
                    {//6
                            {
                                    {1,1,1},
                                    {0,1,1}
                            }
                    },
                    {//7
                            {
                                    {1,1,0},
                                    {1,1,1}
                            }
                    },
                    {//8
                            {
                                    {1,1,1},
                                    {1,1,0}
                            }
                    },
                    {//9
                            {
                                    {1},
                                    {1},
                                    {0}
                            },
                            {
                                    {1},
                                    {1},
                                    {1}
                            },
                    },
                    {//10
                            {
                                    {1},
                                    {1},
                                    {1}
                            },
                            {
                                    {1},
                                    {1},
                                    {0}
                            }
                    },
                    {//11
                            {
                                    {1},
                                    {1},
                                    {1}
                            },
                            {
                                    {0},
                                    {1},
                                    {1}
                            }
                    },
                    {//12
                            {
                                    {0},
                                    {1},
                                    {1}
                            },
                            {
                                    {1},
                                    {1},
                                    {1}
                            },
                    },
                    {////13
                            {
                                    {1,1,0}
                            },
                            {
                                    {1,1,1}
                            },
                    },
                    {////14
                            {
                                    {0,1,1}
                            },
                            {
                                    {1,1,1}
                            },
                    },
                    {////15

                            {
                                    {1,1,1}
                            },
                            {
                                    {1,1,0}
                            }
                    },
                    {////16

                            {
                                    {1,1,1}
                            },
                            {
                                    {0,1,1}
                            }
                    },
                    {//17

                            {
                                    {1, 0}
                            },
                            {
                                    {1, 1}
                            },
                            {
                                    {1, 1}
                            }
                    },
                    {//18

                            {
                                    {0, 1}
                            },
                            {
                                    {1, 1}
                            },
                            {
                                    {1, 1}
                            }
                    },
                    {//19

                            {
                                    {1, 1}
                            },
                            {
                                    {1, 1}
                            },
                            {
                                    {0, 1}
                            }
                    },
                    {//20

                            {
                                    {1, 1}
                            },
                            {
                                    {1, 1}
                            },
                            {
                                    {1, 0}
                            }
                    },
                    {//21

                            {
                                    {1},
                                    {1}
                            },
                            {
                                    {1},
                                    {1}
                            },
                            {
                                    {1},
                                    {0}
                            }
                    },
                    {//22

                            {
                                    {1},
                                    {1}
                            },
                            {
                                    {1},
                                    {1}
                            },
                            {
                                    {0},
                                    {1}
                            }
                    },
                    {//23

                            {
                                    {0},
                                    {1}
                            },
                            {
                                    {1},
                                    {1}
                            },
                            {
                                    {1},
                                    {1}
                            }
                    },
                    {//24

                            {
                                    {1},
                                    {0}
                            },
                            {
                                    {1},
                                    {1}
                            },
                            {
                                    {1},
                                    {1}
                            }
                    }

            },
            {//T
                    {//1
                            {
                                    {1, 1, 1},
                                    {0, 1, 0},
                                    {0, 1, 0}
                            }
                    },
                    {//2
                            {
                                    {1, 0, 0},
                                    {1, 1, 1},
                                    {1, 0, 0}
                            }
                    },
                    {//3
                            {
                                    {0, 1, 0},
                                    {0, 1, 0},
                                    {1, 1, 1}
                            }
                    },
                    {//4
                            {

                                    {0,0,1},
                                    {1,1,1},
                                    {0,0,1}

                            }
                    },
                    {////5
                            {
                                    {0,0,0},
                                    {1,1,1},
                                    {0,0,0}
                            },
                            {
                                    {0,0,0},
                                    {0,1,0},
                                    {0,0,0}
                            },
                            {
                                    {0,0,0},
                                    {0,1,0},
                                    {0,0,0}
                            }
                    },
                    {////6
                            {
                                    {0,1,0},
                                    {0,1,0},
                                    {0,1,0}
                            },
                            {
                                    {0,0,0},
                                    {0,1,0},
                                    {0,0,0}
                            },
                            {
                                    {0,0,0},
                                    {0,1,0},
                                    {0,0,0}
                            }
                    },
                    {////7

                            {
                                    {0,0,0},
                                    {0,1,0},
                                    {0,0,0}
                            },
                            {
                                    {0,0,0},
                                    {0,1,0},
                                    {0,0,0}
                            },
                            {
                                    {0,1,0},
                                    {0,1,0},
                                    {0,1,0}
                            }

                    },
                    {////8

                            {
                                    {0,0,0},
                                    {0,1,0},
                                    {0,0,0}
                            },
                            {
                                    {0,0,0},
                                    {0,1,0},
                                    {0,0,0}
                            },
                            {
                                    {0,0,0},
                                    {1,1,1},
                                    {0,0,0}
                            }

                    },
                    {////9

                            {
                                    {0,1,0},
                                    {0,0,0},
                                    {0,0,0}
                            },
                            {
                                    {0,1,0},
                                    {0,1,0},
                                    {0,1,0}
                            },
                            {
                                    {0,1,0},
                                    {0,0,0},
                                    {0,0,0}
                            }

                    },
                    {////10

                            {
                                    {0,0,0},
                                    {1,0,0},
                                    {0,0,0}
                            },
                            {
                                    {0,0,0},
                                    {1,1,1},
                                    {0,0,0}
                            },
                            {
                                    {0,0,0},
                                    {1,0,0},
                                    {0,0,0}
                            }

                    },
                    {////11

                            {
                                    {0,0,0},
                                    {0,0,0},
                                    {0,1,0}
                            },
                            {
                                    {0,1,0},
                                    {0,1,0},
                                    {0,1,0}
                            },
                            {
                                    {0,0,0},
                                    {0,0,0},
                                    {0,1,0}
                            }
                    }
                    ,
                    {////12
                            {
                                    {0,0,0},
                                    {0,0,1},
                                    {0,0,0}
                            },
                            {
                                    {0,0,0},
                                    {1,1,1},
                                    {0,0,0}
                            },
                            {
                                    {0,0,0},
                                    {0,0,1},
                                    {0,0,0}
                            }

                    }
            }

    };
}