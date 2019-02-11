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
        import { mapActions } from 'vuex'

        export default {
        props : ['messageAttr'],
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
            ...mapActions(['addMessageAction', 'updateMessageAction']),
            save() {
                const message = {id: this.id, message: this.message};
                if(this.id){
                    this.updateMessageAction(message)
                }else {
                    this.addMessageAction(message)
                }
                this.message = ''
                this.id = ''
            }
        }
    }
</script>

<style>

</style>