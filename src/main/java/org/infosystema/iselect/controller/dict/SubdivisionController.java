package org.infosystema.iselect.controller.dict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.beans.SortEnum;
import org.infosystema.iselect.model.Subdivision;
import org.infosystema.iselect.service.SubdivisionService;
import org.infosystema.iselect.util.web.LoginUtil;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
public class SubdivisionController implements Serializable{


	private static final long serialVersionUID = 1L;
	private TreeNode dictRoot;
	private TreeNode dictSearch; 
	private TreeNode selectedNode; 
	private String searchWord="";
	
	private Subdivision selectedDictionary;
	private Subdivision selectedDictionaryParent;
	private Boolean forDelete = Boolean.FALSE;
	
	@EJB
	private SubdivisionService  dictionaryService; 
	@Inject 
	LoginUtil loginUtil;
	
	private Integer step=0;
	
	@PostConstruct
	public void init() {  
		initRootTree();
    }

	private void initRootTree() {
		dictRoot = new DefaultTreeNode("Root", null);  
		dictRoot.setExpanded(true);

        List<FilterExample> examples = new ArrayList<>();
        
        examples.add(new FilterExample("id",1,InequalityConstants.EQUAL));
        initTreeData(dictRoot, examples, true);
	}
	
	public void searchDict(){
		List<FilterExample> examples = new ArrayList<>();
		if(searchWord.length()<2)return;
		setDictSearch(new DefaultTreeNode("Root", null)); 
		
		System.out.println("=======search======="+searchWord);
		
		examples.add(new FilterExample("code", "%"+searchWord+"%" , InequalityConstants.LIKE));
        examples.add(new FilterExample("name", "%"+searchWord+"%" , InequalityConstants.LIKE));
        
        List<Subdivision> dictionaries = dictionaryService.findByExample(0, 50, SortEnum.ASCENDING, examples, "code");
        
        for(Subdivision dict : dictionaries) {
        	
        	new DefaultTreeNode("dict", dict, dictSearch);
        }
		
	}
	  
    public void onNodeExpand(NodeExpandEvent event) {  
        System.out.println("2222222222222");  
        TreeNode nodeParent= event.getTreeNode();
       	nodeParent.getChildren().clear();
       	
       	if (nodeParent.getType().equals("dict")) {
	         
       		Subdivision dictParent = (Subdivision)nodeParent.getData();
		    
	        List<FilterExample> examples = new ArrayList<>();
		    examples.add(new FilterExample("parent", dictParent, InequalityConstants.EQUAL));
		    
		    initTreeData(nodeParent, examples, false);
       	}  
    }

	private void initTreeData(TreeNode nodeParent, List<FilterExample> examples,  boolean flag) {
     
		for (Subdivision dict : dictionaryService.findByExample(0, 9999, examples)) {
			examples.clear();

			examples.add(new FilterExample("parent", dict, InequalityConstants.EQUAL));
			
			if ( dictionaryService.countByExample(examples) > 0 ) {
				DefaultTreeNode node = new DefaultTreeNode("dict", dict, nodeParent);
				System.out.println(dict.getCode() + "-- " + dict.getName() + " -- " + flag);
		    	TreeNode treeNode = null;
		    	if(!flag) treeNode = new DefaultTreeNode(null, node);
		    	if(flag) initTreeData(treeNode == null ? node : treeNode, examples, false);
		    	node.setExpanded(flag);
		    	nodeParent.setExpanded(true);
		    } else {
		    	new DefaultTreeNode("withoutChildren", dict, nodeParent);	 
		    }
		}
	}

    public void onContextMenuAdd() {
    	forDelete = Boolean.FALSE;
    	Subdivision parentDict = (Subdivision)selectedNode.getData();
    	selectedDictionary = new Subdivision();
    	selectedDictionary.setParent(parentDict);
    	
    	selectedDictionaryParent = dictionaryService.findById(parentDict.getId(),false);
    }

    public void onContextMenuEdit() {
    	forDelete = Boolean.FALSE;
    	selectedDictionary = (Subdivision)selectedNode.getData();
    	selectedDictionaryParent = dictionaryService.findById(selectedDictionary.getId(),false);
    }

    public void onContextMenuDelete() {
    	forDelete = Boolean.TRUE;
    	selectedDictionary = (Subdivision)selectedNode.getData();
    	selectedDictionaryParent = dictionaryService.findById(selectedDictionary.getId(),false);
    }

	public String saveDict() {	
		if(selectedDictionary.getId()==null){
			selectedDictionary = dictionaryService.persist(selectedDictionary);
			
			selectedNode.setType("dict");
			new DefaultTreeNode("withoutChildren", selectedDictionary, selectedNode);
		}
		else{
			selectedDictionary = dictionaryService.merge(selectedDictionary);		
		}
		
		selectedDictionary = null;
		return "/view/subdivision/subdivision_tree.xhtml?faces-redirect=true";
	}



	public String deleteDict() {  	
		try {
			TreeNode parent = selectedNode.getParent();
	    	parent.getChildren().remove(selectedNode);
	    	parent.setType("withoutChildren");
	    	
	    	dictionaryService.remove(selectedDictionary);
	    	selectedDictionary = null;
		} catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Справочник не может быть удален!!!", "Имеются связи с другими справочниками!!!"));
			e.printStackTrace();
		}
    	return "/view/subdivision/subdivision_tree.xhtml?faces-redirect=true";
	}
	
	public void dictTypeChanged() {
		initRootTree();
	}

	public TreeNode getDictRoot() {
		return dictRoot;
	}

	public void setDictRoot(TreeNode dictRoot) {
		this.dictRoot = dictRoot;
	}

	public TreeNode getDictSearch() {
		return dictSearch;
	}

	public void setDictSearch(TreeNode dictSearch) {
		this.dictSearch = dictSearch;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public Subdivision getSelectedDictionary() {
		return selectedDictionary;
	}
	
	public void setSelectedDictionary(Subdivision selectedDictionary) {
		this.selectedDictionary = selectedDictionary;
	}

	public Subdivision getSelectedDictionaryParent() {
		return selectedDictionaryParent;
	}
	
	public void setSelectedDictionaryParent(Subdivision selectedDictionaryParent) {
		this.selectedDictionaryParent = selectedDictionaryParent;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public Boolean getForDelete() {
		return forDelete;
	}

	public void setForDelete(Boolean forDelete) {
		this.forDelete = forDelete;
	}

}
