<template>
    <v-layout row>
        <v-text-field label="New Message"
                      placeholder="Write message"
                      v-model="message"/>
        <v-btn @click="save">
            Save
        </v-btn>
    </v-layout>
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