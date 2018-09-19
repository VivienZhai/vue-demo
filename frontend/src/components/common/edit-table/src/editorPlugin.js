import TextEditor from "./editors/text-editor";
import SelectEditor from "./editors/select-editor";
import HelpboxEditor from "./editors/helpbox-editor";
import DateEditor from "./editors/date-editor";
export default{
    functional: true,
    render:function(createElement,context){
        var editorType = context.props.column.editor.type;
        if(editorType === "text"){
          return createElement(TextEditor, context.data, context.children);
        }else if(editorType === "select"){
          return createElement(SelectEditor, context.data, context.children);
        }else if(editorType === "helpbox"){
          return createElement(HelpboxEditor, context.data, context.children);
        }else if(editorType === "date"){
          return createElement(DateEditor, context.data, context.children);
        }else if(editorType === "daterange"){
          return createElement(DateEditor, context.data, context.children);
        }else if(editorType === "year"){
          return createElement(DateEditor, context.data, context.children);
        }else if(editorType === "month"){
          return createElement(DateEditor, context.data, context.children);
        }else{
          console.error("无法识别的编辑器：" + editorType);
          return createElement('span', context.props.row[context.props.column.key]);
        }
    }
}
