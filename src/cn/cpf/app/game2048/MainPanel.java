package cn.cpf.app.game2048;


import javax.swing.JPanel;

public class MainPanel extends JPanel {
    private static final long serialVersionUID = 1438311206812223394L;
    private Cell[] cells = new Cell[16];
    private int[][] cellsNumber0 = {{0, 0, 0, 0}, {0, 2, 4, 0}, {0, 4, 2, 0}, {0, 0, 0, 0}};
    private int[][] cellsNumber = new int[4][4];
    private int[][] mixure = new int[4][4];
    private int[] cellNum = {2, 4, 4, 2};
    private int hang = 4, lie = 4;
    protected int sum;
    public boolean successFlag, falseFlag;

    /**
     * Create the panel.
     */
    public MainPanel() {
        super();
        // 设置空布局
        setLayout(null);
        // 初始化
        init();

    }


    /**
     * 初始化操作
     */
    public void init() {
        // 图标对象
        Cell cell = null;
        successFlag = false;
        falseFlag = false;
        sum = 4;
        // 循环行
        for (int i = 0; i < cells.length; i++) {
            // 实例化单元图片对象
            cell = new Cell();
            // 设置单元图片的坐标
            cell.setLocation(i % 4 * 100, i / 4 * 50);
            // 将单元图片旋转到单元图片数组中
            cells[i] = cell;
        }
        // 向面板中添加所有单元图片
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < lie; j++) {
                cellsNumber[i][j] = cellsNumber0[i][j];
                if (cellsNumber0[i][j] == 0) cells[i * 4 + j].setText("");
                else cells[i * 4 + j].setText(String.valueOf(cellsNumber0[i][j]));
                this.add(cells[i * 4 + j]);
            }
        }
    }


    public void randon() {
        int numb = 0;
        // 2,4,8,16中的随机数
        int ran_Order = cellNum[(int) (Math.random() * 4)];
        for (int i = 0; i < hang; i++)
            for (int l = 0; l < lie; l++)
                if (cellsNumber[i][l] == 0)
                    numb++;
        // 随机选取的空白Textfield
        if (numb == 0) falseFlag = true;
        int p1 = (int) (java.lang.Math.random() * numb);
        //将选取的随机数赋予相应的存储cellsNum
        for (int i = 0; i < hang; i++)
            for (int l = 0; l < lie; l++) {
                if (cellsNumber[i][l] == 0)
                    if (p1-- == 0) {
                        cellsNumber[i][l] = ran_Order;
                    }
            }
        updata();
    }


    public void updata() {
        for (int i = 0; i < hang; i++)
            for (int l = 0; l < lie; l++)
                if (cellsNumber[i][l] == 0) cells[i * 4 + l].setText("");
                else cells[i * 4 + l].setText(String.valueOf(cellsNumber[i][l]));
    }


    /**
     * 向上移动触发的方法
     */
    public void Up() {
        for (int i = 0; i < hang; i++)
            for (int j = 0; j < lie; j++)
                mixure[i][j] = cellsNumber[j][i];
        suanfa();
        for (int i = 0; i < hang; i++)
            for (int j = 0; j < lie; j++)
                cellsNumber[i][j] = mixure[j][i];
        randon();
    }


    /**
     * 向下移动触发的方法
     */
    public void Down() {
        for (int i = 0; i < hang; i++)
            for (int j = 0; j < lie; j++)
                mixure[i][j] = cellsNumber[hang - 1 - j][i];
        suanfa();
        for (int i = 0; i < hang; i++)
            for (int j = 0; j < lie; j++)
                cellsNumber[i][j] = mixure[j][hang - i - 1];
        randon();
    }


    /**
     * 向左移动触发的方法
     */
    public void Left() {
        for (int i = 0; i < hang; i++)
            for (int j = 0; j < lie; j++)
                mixure[i][j] = cellsNumber[i][j];
        suanfa();
        for (int i = 0; i < hang; i++)
            for (int j = 0; j < lie; j++)
                cellsNumber[i][j] = mixure[i][j];
        randon();
    }


    /**
     * 向右移动触发的方法
     */
    public void Right() {
        for (int i = 0; i < hang; i++)
            for (int j = 0; j < lie; j++)
                mixure[i][j] = cellsNumber[i][lie - j - 1];
        suanfa();
        for (int i = 0; i < hang; i++)
            for (int j = 0; j < lie; j++)
                cellsNumber[i][j] = mixure[i][lie - j - 1];
        randon();
    }


    public void suanfa() {
        for (int i = 0; i < hang; i++) {
            int j = 0, k = 0, sin;
            int pp[] = new int[7];
            for (int e = 0; e < lie; e++)
                pp[e] = mixure[i][e];

            boolean flag1 = false;
            while (!flag1) {
                flag1 = true;
                label:
                for (j = 0; j < lie - 1; j++) {
                    sin = j;
                    while (pp[j] == 0)
                        if (j >= lie - 1) break label;
                        else j++;
                    k = j + 1;
                    if (sin < j) {
                        pp[sin] = pp[j];
                        pp[j] = 0;
                        j = sin;
                    }
                    while (pp[k] == 0) {
                        if (k >= lie - 1) break label;
                        k++;
                    }
                    if (pp[j] == pp[k]) {
                        pp[j] = pp[j] * 2;
                        pp[k] = 0;
                        if (sum < pp[j]) sum = pp[j];
                        flag1 = false;
                    } else if (k > j + 1) {
                        pp[j + 1] = pp[k];
                        pp[k] = 0;
                    }
                }
            }
            for (int e = 0; e < lie; e++)
                mixure[i][e] = pp[e];
        }

    }

}
