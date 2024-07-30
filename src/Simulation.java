package src;

import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;
import src.entities.staticEntities.Grass;

import java.util.LinkedList;
import java.util.Queue;


public class Simulation {
    public static void main(String[] args) {
        Actions.addEntitiesOnMap(1,3);
        WorldMap.renderWorldMap();
        System.out.println(Herbivore.getHerbivoresPositions());
        System.out.println(Predator.getPredatorsPositions());
        System.out.println(Grass.getGrassPositions());
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
