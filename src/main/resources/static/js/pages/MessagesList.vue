<template>
    <v-container>
        <v-layout align-space-around justify-start column>
            <message-form :messageAttr="message"/>
            <message-row v-for="message in sortedMessages"
                         :key="message.id"
                         :message="message"
                         :editMessage="editMessage"/>
        </v-layout>
    </v-container>
</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import { mapGetters, mapMutations } from 'vuex'

    export default {
        components:{
            MessageRow,
            MessageForm
        },
        methods: mapMutations([
           'removeMessageMutation'
        ]),
        data(){
            return {
                message : null
            }
        },
        computed: mapGetters(['sortedMessages']),
        methods : {
            editMessage(message) {
                this.message = message
            }
        }
    }
</script>

<style>

</style>