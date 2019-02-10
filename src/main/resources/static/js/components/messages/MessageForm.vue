<template>
    <div>
        <input type="text" placeholder="Write message" v-model="message"/>
        <input type="button" value="Save" @click="save"/>
    </div>
</template>

<script>
    import { sendMessage } from "../../../util/ws";

    export default {
        props : ['messages', 'messageAttr'],
        data(){
            return {
                message : '',
                id: ''
            }
        },
        watch : {
            messageAttr(newVal, oldVal){
                this.message = newVal.message
                this.id = newVal.id
            }
        },
        methods: {
            save() {
                sendMessage({id: this.id, message: this.message})
                this.message = ''
                this.id = ''
            }
        }
    }
</script>

<style>

</style>