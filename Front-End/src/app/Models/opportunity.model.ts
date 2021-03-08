export class Opportunity {

    public id: number;
    public userId:String;
    public description: String;
    public date: string;
    public skills: any;
    public location: String;
    public minExperience: number;
    public demand: number;

    public constructor() {}

    public default () {
        this.id = 0;
        this.userId = "";
        this.description = "";
        this.date = "";
        this.skills = "";
        this.location = "";
        this.minExperience = 0;
        this.demand = 0;
    }
}
