export interface Course {
    id?: number,
    creationUser?: string,
    modificationUser?: string,
    creationDate?: Date,
    updateDate?: Date,
    title?: string,
    description?: string,
    author?: string,
    tags?: string,
    courseType?: string,
    rating?: number
}