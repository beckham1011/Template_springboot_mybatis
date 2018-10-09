package cn.bjjoy.bms.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TreeGenerate{

	@SuppressWarnings("unchecked")
	private List<Node> buildListToTree(List<Node> dirs){
		List<Node> roots = findRoots(dirs);
		List<Node> notRoots = (List<Node>)CollectionUtils.subtract(dirs,roots);
		for(Node root : roots){
			root.setChildren(findChildren(root,notRoots));
		}
		return roots;
	}
	
	public List<Node> findRoots(List<Node>allNodes){
		List<Node>results=new ArrayList<Node>();
		for(Node node : allNodes){
			boolean isRoot=true;
				for(Node comparedOne : allNodes){
				if(node.getParentId() == comparedOne.getId()){
					isRoot = false;
					break;
				}
			}
			if(isRoot){
				node.setLevel(0);
				results.add(node);
				node.setRootId(node.getId());
			}
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	private List<Node> findChildren(Node root, List<Node> allNodes){
		List<Node>children = new ArrayList<Node>();
			for(Node comparedOne:allNodes){
				if(comparedOne.getParentId() == root.getId()){
				comparedOne.setParent(root);
				comparedOne.setLevel(root.getLevel() + 1);
				children.add(comparedOne);
			}
		}
			
		List<Node> notChildren = (List<Node>)CollectionUtils.subtract(allNodes,children);
		for(Node child:children){
			List<Node> tmpChildren = findChildren(child,notChildren);
			if(tmpChildren == null || tmpChildren.size()<1){
				child.setLeaf(true);
			}else{
				child.setLeaf(false);
			}
			child.setChildren(tmpChildren);
		}
		return children;
	}
	
	public JSONArray generateTree(List<Node> nodes){
		TreeGenerate tb = new TreeGenerate();
		List<Node> roots = tb.buildListToTree(nodes);
		
		JSONArray array=new JSONArray();
		JSONObject o = null ;
		for(Node  node :roots){
			o = new JSONObject();
			o.put("id", node.getId());
			o.put("name", node.getName());
			List<Node> child = node.getChildren();
			array.add(o);
		
			if(child!=null&&child.size()>0){
				tb.fillChildren(o,child);
			}
		}
		return array  ;
	}
	
	public static void main(String[]args){
		TreeGenerate tb = new TreeGenerate();
		
		List<Node> allNodes = new ArrayList<Node>();
		allNodes.add(new Node (1,0,"节点1"));
		allNodes.add(new Node (2,0,"节点2"));
		allNodes.add(new Node (3,0,"节点3"));
		allNodes.add(new Node (11,7,"节点11"));
		allNodes.add(new Node (4,1,"节点4"));
		allNodes.add(new Node (5,1,"节点5"));
		allNodes.add(new Node (6,1,"节点6"));
		allNodes.add(new Node (7,4,"节点7"));
		allNodes.add(new Node (8,4,"节点8"));
		allNodes.add(new Node (9,5,"节点9"));
		allNodes.add(new Node (10,100,"节点10"));
		
		tb.generateTree(allNodes) ;
		
	}
	
	private void fillChildren(JSONObject o,List<Node>childs){
		JSONArray array=new JSONArray();
		for(Node node : childs){
			JSONObject oo = new JSONObject();
			List<Node> child = node.getChildren();
			oo.put("id", node.getId());
			oo.put("name", node.getName());
			array.add(oo);
			if(child!=null&&child.size()>0){
				fillChildren(oo,child);
			}
		}
		o.put("child",array);
	}

	
}
