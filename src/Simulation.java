package src;

import src.entities.Entity;
import src.entities.creatures.Creature;
import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Simulation {
    public static void main(String[] args) throws InterruptedException {
        Actions.addEntitiesOnMap(2, 5);
        WorldMap.renderWorldMap();
        List<Creature> creatures = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entity : WorldMap.getWorld().entrySet()) {
            if (entity.getValue() instanceof Creature) {
                creatures.add((Creature) entity.getValue());
            }
        }


        while (true) {
            for (Creature creature : creatures){
                if (creature instanceof Predator){
                    Predator predator = (Predator) creature;
                    predator.makeMove();
                } else if (creature instanceof Herbivore) {
                    Herbivore herbivore = (Herbivore) creature;
                    herbivore.makeMove();
                }
            }
            System.out.println();
            System.out.println();
            WorldMap.renderWorldMap();
            Thread.sleep(500);
        }


    }

//    static class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//
//        public TreeNode() {
//        }
//
//        public TreeNode(int val) {
//            this.val = val;
//        }
//
//        public TreeNode(int val, TreeNode left, TreeNode right) {
//            this.val = val;
//            this.left = left;
//            this.right = right;
//        }
//    }

//    public static void main(String[] args) {
//        TreeNode t1 = new TreeNode(1, new TreeNode(2,new TreeNode(3),new TreeNode(4)), new TreeNode(2,new TreeNode(3),new TreeNode(4)));
//    }
//
//
//
//
//    public  static boolean isMirror(){
//        return true;
//    }
//
//    public static boolean isSameTree(TreeNode p, TreeNode q) {
//        Queue<TreeNode> queueP = new LinkedList<>();
//        Queue<TreeNode> queueQ = new LinkedList<>();
//        StringBuilder sbP = new StringBuilder();
//        StringBuilder sbQ = new StringBuilder();
//        if (p==null || q==null){
//            return true;
//        }
//        queueP.add(p);
//        queueQ.add(q);
//
//        while (!queueQ.isEmpty()) {
//            TreeNode tQ = queueQ.poll();
//            sbQ.append(tQ.val);
//            if (tQ.left == null) {
//                sbQ.append("null");
//            } else {
//                queueQ.add(tQ.left);
//                sbQ.append(tQ.left.val);
//            }
//            if (tQ.right == null) {
//                sbQ.append("null");
//            } else {
//                queueQ.add(tQ.right);
//                sbQ.append(tQ.right.val);
//            }
//        }
//        while (!queueP.isEmpty()) {
//            TreeNode tP = queueP.poll();
//            sbP.append(tP.val);
//            if (tP.left == null) {
//                sbP.append("null");
//            } else {
//                queueP.add(tP.left);
//                sbP.append(tP.left.val);
//            }
//            if (tP.right == null) {
//                sbP.append("null");
//            } else {
//                queueP.add(tP.right);
//                sbP.append(tP.right.val);
//            }
//        }
//
//        return sbP.toString().equals(sbQ.toString());
//    }

}
